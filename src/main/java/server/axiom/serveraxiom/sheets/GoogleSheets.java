package server.axiom.serveraxiom.sheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.api.client.googleapis.auth.oauth2.GoogleCredential.fromStream;

public class GoogleSheets {

    private static final String CREDENTIALS_FILE_PATH = "serveraxiom-14649f51fc3e.json";
    private static final String APPLICATION_NAME = "google-sheet-project";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {

        ClassLoader loader = GoogleSheets.class.getClassLoader();
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(loader.getResource(CREDENTIALS_FILE_PATH).getFile()))
                .createScoped(SCOPES);

        return credential;
    }

    public static void test() throws GeneralSecurityException, IOException {

        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        String spreadSheetId = "1ReDa2y0crFuyaldqYXTROFI8kEVwYFMlKXh5bAkzPmw";
        String range = "Axiom1!A1:C4";

        List<List<Object>> values = Arrays.asList(
                Arrays.asList("Name", "Age", "Gender"),
                Arrays.asList("Alice", 25, "Female"),
                Arrays.asList("Bob", 30, "Male"),
                Arrays.asList("choi", 32, "fmale1111111111"));
        ValueRange data = new ValueRange().setValues(values);

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        service.spreadsheets().values().update(spreadSheetId, range, data)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }



}
