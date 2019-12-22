# commercetools Correlation Id Decorator

Java Library used to attach a correlation id (as input by the user of the library) value as the header `X_CORRELATION_ID` to any 
commercetools-jvm-sdk SphereRequest.


### Usage

```java
// 1. Decorate SphereRequest with Request with correlation id.
Request request = Request.of(ProjectGet.of(), "my-unique-correlation-id");

// 2. execute request
sphereClient.execute().toCompletebleFuture().join();
```
