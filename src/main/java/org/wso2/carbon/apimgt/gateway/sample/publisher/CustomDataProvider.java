package org.wso2.carbon.apimgt.gateway.sample.publisher;

import org.apache.synapse.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.common.analytics.collectors.AnalyticsCustomDataProvider;

import java.util.HashMap;
import java.util.Map;

public class CustomDataProvider implements AnalyticsCustomDataProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomDataProvider.class);
    private static final String TOKEN_ISSUER = "issuer";

    public CustomDataProvider(Map<String, String> properties) {
        log.info("Successfully initialized");
    }

    @Override
    public Map<String, Object> getCustomProperties(Object messageContext) {
        if (messageContext instanceof MessageContext) {
            Map<String, Object> customProperties = new HashMap<>();
            customProperties.put("tokenIssuer", getTokenIssuer((MessageContext) messageContext));
        }
        return null;
    }

    private String getTokenIssuer(MessageContext messageContext) {

        if (messageContext.getPropertyKeySet().contains(TOKEN_ISSUER)) {
            return (String) messageContext.getProperty(TOKEN_ISSUER);
        }
        return null;
    }

}
