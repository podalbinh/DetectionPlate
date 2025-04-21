import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.LicensePlate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import dao.LicensePlateDao;

@WebServlet("/video-upload")
@MultipartConfig
public class VideoUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();

        List<LicensePlate> plates = callApiToGetLicensePlates(fileContent);

        LicensePlateHandler detectionHandler = new LPDetectionHandler();
        LicensePlateHandler unregisteredHandler = new UnregisteredLPHandler();

        detectionHandler.setNextHandler(unregisteredHandler);

        HttpSession session = request.getSession();
        detectionHandler.handle(plates, session);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private List<LicensePlate> callApiToGetLicensePlates(InputStream fileContent) throws IOException {
        String url = "http://127.0.0.1:5000/detect-plates";
        List<LicensePlate> plates = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(url);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "video",
                    fileContent,
                    ContentType.APPLICATION_OCTET_STREAM,
                    "video.mp4"
            );

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            try (CloseableHttpResponse response = httpClient.execute(uploadFile)) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(responseString);
                    JsonNode detectedImagesNode = rootNode.path("detected_images");

                    for (JsonNode node : detectedImagesNode) {
                        LicensePlate plate = objectMapper.treeToValue(node, LicensePlate.class);
                        plates.add(plate);
                    }
                }
            }
        }

        return plates;
    }
}