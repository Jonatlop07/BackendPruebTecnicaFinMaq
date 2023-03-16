package com.jonatlop.server.core.abstraction.interactor;

public interface Interactor<InputModel, OutputModel> {
    OutputModel execute(InputModel input);
}
