package com.social.network.utils;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.social.network.exceptions.CannotExtractLinkDataException;
import com.social.network.model.dto.LinkDataDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class OpenGraphUtil {

    private static final String OG_DESCRIPTION_PROPERTY = "meta[property=og:description]";
    private static final String DESCRIPTION_PROPERTY = "meta[property=description]";
    private static final String DESCRIPTION_NAME = "meta[name=description]";

    private static final List<String> DESCRIPTION_CSS_QUERIES = Arrays.asList(OG_DESCRIPTION_PROPERTY, DESCRIPTION_NAME, DESCRIPTION_PROPERTY);

    public static LinkDataDto parseHtmlMetaTags(final String url) {
        final Retryer<LinkDataDto> retrying = RetryerBuilder.<LinkDataDto>newBuilder().retryIfResult(Objects::isNull)
                .retryIfExceptionOfType(Exception.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .build();
        try {
            return retrying.call(() -> {
                try {
                    final LinkDataDto linkData = new LinkDataDto();
                    linkData.setUrl(url);
                    final Document doc = Jsoup.connect(url)
                            .get();
                    final Elements metaTitle = doc.select("meta[property=og:title]");
                    if (metaTitle != null && !metaTitle.isEmpty()) {
                        linkData.setTitle(metaTitle.first()
                                .attr("content"));
                    } else {
                        linkData.setTitle(doc.title());
                    }
                    final Elements metaImage = doc.select("meta[property=og:image]");
                    if (metaImage != null && !metaImage.isEmpty()) {
                        linkData.setImageUrl(metaImage.first()
                                .attr("content"));
                    }
                    for (final String descriptionCssQuery : DESCRIPTION_CSS_QUERIES) {
                        final Elements description = doc.select(descriptionCssQuery);
                        if (description != null && !description.isEmpty()) {
                            linkData.setDescription(description.first()
                                    .attr("content"));
                            break;
                        }
                    }
                    return linkData;
                } catch (Exception e) {
                    //LOG.error(e.getLocalizedMessage(), e);
                    throw e;
                }
            });
        } catch (Exception e) {
            throw new CannotExtractLinkDataException();
        }
    }
}
