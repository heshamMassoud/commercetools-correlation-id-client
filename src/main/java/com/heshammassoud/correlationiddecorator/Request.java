package com.heshammassoud.correlationiddecorator;

import io.sphere.sdk.client.HttpRequestIntent;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.client.SphereRequestDecorator;
import io.sphere.sdk.http.HttpHeaders;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Request<T> extends SphereRequestDecorator<T> {

    private final String correlationId;

    private Request(@Nonnull final SphereRequest<T> delegate, @Nonnull final String correlationId) {
        super(delegate);
        this.correlationId = correlationId;
    }

    /**
     * Decorates a {@link SphereRequest} with this {@link Request}. The main function of decoration is in the
     * {@link Request#httpRequestIntent()} which stores the supplied {@code correlationId} as a header in the request
     * with the key {@value HttpHeaders#X_CORRELATION_ID}.
     *
     * @param delegate the delegate {@link SphereRequest} to be decorated.
     * @param correlationId the correlation id to store in the header of the decorated request.
     * @param <T> the type of the resource resulting from performing the request on the commercetools platform.
     * @return
     */
    public static <T> Request<T> of(@Nonnull final SphereRequest<T> delegate, @Nonnull final String correlationId) {
        return new Request<>(delegate, correlationId);
    }

    @Override
    public HttpRequestIntent httpRequestIntent() {
        final HttpRequestIntent httpRequestIntent = super.httpRequestIntent();
        return httpRequestIntent.plusHeader(HttpHeaders.X_CORRELATION_ID, correlationId);
    }

    @Override
    public boolean equals(Object otherRequest) {
        if (this == otherRequest) {
            return true;
        }
        if (!(otherRequest instanceof Request)) {
            return false;
        }
        if (!super.equals(otherRequest)) {
            return false;
        }
        final Request<?> that = (Request<?>) otherRequest;
        return correlationId.equals(that.correlationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), correlationId);
    }
}
