package com.jonatlop.server.core.abstraction.usecase;

public interface Interactor<InputModel, OutputModel> {
    OutputModel execute(InputModel input);
}
