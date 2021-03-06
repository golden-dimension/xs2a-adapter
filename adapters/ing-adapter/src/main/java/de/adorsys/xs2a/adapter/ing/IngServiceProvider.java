package de.adorsys.xs2a.adapter.ing;

import de.adorsys.xs2a.adapter.api.*;
import de.adorsys.xs2a.adapter.api.http.HttpClientFactory;
import de.adorsys.xs2a.adapter.api.link.LinksRewriter;
import de.adorsys.xs2a.adapter.api.model.Aspsp;
import de.adorsys.xs2a.adapter.impl.AbstractAdapterServiceProvider;
import de.adorsys.xs2a.adapter.impl.link.identity.IdentityLinksRewriter;

public class IngServiceProvider extends AbstractAdapterServiceProvider implements Oauth2ServiceProvider {

    private static final LinksRewriter DEFAULT_LINKS_REWRITER = new IdentityLinksRewriter();

    @Override
    public AccountInformationService getAccountInformationService(Aspsp aspsp,
                                                                  HttpClientFactory httpClientFactory,
                                                                  LinksRewriter linksRewriter) {
        return getIngAccountInformationService(aspsp, httpClientFactory, linksRewriter);
    }

    private IngAccountInformationService getIngAccountInformationService(Aspsp aspsp,
                                                                         HttpClientFactory httpClientFactory,
                                                                         LinksRewriter linksRewriter) {
        return new IngAccountInformationService(IngApiFactory.getAccountInformationApi(aspsp, httpClientFactory),
            ingOauth2Service(aspsp, httpClientFactory),
            linksRewriter,
            getInterceptors(aspsp));

    }

    private IngOauth2Service ingOauth2Service(Aspsp aspsp, HttpClientFactory httpClientFactory) {
        return new IngOauth2Service(IngApiFactory.getOAuth2Api(aspsp, httpClientFactory),
            IngClientAuthenticationFactory.getClientAuthenticationFactory(httpClientFactory.getHttpClientConfig().getKeyStore()),
            getInterceptors(aspsp));
    }

    @Override
    public Oauth2Service getOauth2Service(Aspsp aspsp,
                                          HttpClientFactory httpClientFactory) {
        return getIngAccountInformationService(aspsp,
            httpClientFactory,
            DEFAULT_LINKS_REWRITER);
    }

    @Override
    public String getAdapterId() {
        return "ing-adapter";
    }

    @Override
    public PaymentInitiationService getPaymentInitiationService(Aspsp aspsp,
                                                                HttpClientFactory httpClientFactory,
                                                                LinksRewriter linksRewriter) {
        return new IngPaymentInitiationService(IngApiFactory.getPaymentInitiationApi(aspsp, httpClientFactory),
            ingOauth2Service(aspsp, httpClientFactory),
            linksRewriter,
            getInterceptors(aspsp));
    }
}
