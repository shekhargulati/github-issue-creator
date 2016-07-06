package ghic;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GithubIssueCreatorTest {

    @Test
    public void shouldCreateIssues() throws Exception {
        List<IssueRequest> issueRequests = Files.lines(Paths.get("src", "test", "resources", "issues.txt"))
                .map(l -> String.format("Add %s function", l))
                .map(title -> new IssueRequest(title, 1, new String[]{"function"}, "shekhargulati"))
                .collect(Collectors.toList());

        final String oauthToken = System.getenv("GITHUB_OAUTH_ACCESS_TOKEN");
        GithubIssueCreator githubIssueCreator = GithubIssueCreator.newInstance(oauthToken, "shekhargulati", "lodash4j");
        List<Issue> issues = githubIssueCreator.createIssues(issueRequests);

        issues.forEach(System.out::println);
        Assert.assertThat(issues.size(), CoreMatchers.equalTo(62));
    }
}