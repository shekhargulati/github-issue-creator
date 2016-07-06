package ghic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GithubIssueCreator {

    private final String oauthToken;
    OkHttpClient client = new OkHttpClient();
    private final String apiUrl;


    private GithubIssueCreator(final String oauthToken, final String owner, final String repo) {
        this.oauthToken = oauthToken;
        this.apiUrl = String.format("https://api.github.com/repos/%s/%s/issues", owner, repo);
    }


    public static GithubIssueCreator newInstance(final String oauthToken, final String owner, final String repo) {
        return new GithubIssueCreator(oauthToken, owner, repo);
    }

    public List<Issue> createIssues(List<IssueRequest> issues) {
        return issues.stream().map(issue -> {
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(issue);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("Authorization", String.format("token %s", oauthToken))
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                    .build();
            Call call = client.newCall(request);
            try {
                Response response = call.execute();
                String responseJson = response.body().string();
                return gson.fromJson(responseJson, Issue.class);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to create issue %s", issue.getTitle()), e);
            }
        }).collect(Collectors.toList());
    }


}
