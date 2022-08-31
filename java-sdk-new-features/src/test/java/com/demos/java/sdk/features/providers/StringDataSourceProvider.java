package com.demos.java.sdk.features.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class StringDataSourceProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(" satheesh ", "satheesh"),
                Arguments.of("satheesh ", "satheesh"),
                Arguments.of(" satheesh", "satheesh")
        );
    }
}
