package com.heshammassoud.correlationiddecorator;


import io.sphere.sdk.http.HttpHeaders;
import io.sphere.sdk.projects.Project;
import io.sphere.sdk.projects.queries.ProjectGet;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    @Test
    void of_WithRandomUuidCorrelationId_ShouldBeStoredInRequestHeader() {
        // preparation
        final String correlationId = UUID.randomUUID().toString();
        // test
        final Request<Project> request = Request.of(ProjectGet.of(), correlationId);
        // assertion
        assertThat(request.httpRequestIntent().getHeaders())
            .isEqualTo(HttpHeaders.of(HttpHeaders.X_CORRELATION_ID, correlationId));
    }
}
