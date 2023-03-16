package com.jonatlop.server.core.abstraction.interactor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface InteractorExecutor {
    <RX, I, O>CompletableFuture<RX> execute(
      Interactor<I, O> interactor,
      I inputModel,
      Function<O, RX> responseMapper
    );
}
