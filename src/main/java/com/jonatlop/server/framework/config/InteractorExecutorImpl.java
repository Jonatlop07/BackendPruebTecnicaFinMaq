package com.jonatlop.server.framework.config;

import com.jonatlop.server.core.abstraction.interactor.Interactor;
import com.jonatlop.server.core.abstraction.interactor.InteractorExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class InteractorExecutorImpl implements InteractorExecutor {
    
    @Override
    public <RX, I, O> CompletableFuture<RX> execute(
        Interactor<I, O> interactor, I inputModel, Function<O, RX> responseMapper
    ) {
        return CompletableFuture
            .supplyAsync(() -> inputModel)
            .thenApplyAsync(interactor::execute)
            .thenApplyAsync(responseMapper);
    }
}
