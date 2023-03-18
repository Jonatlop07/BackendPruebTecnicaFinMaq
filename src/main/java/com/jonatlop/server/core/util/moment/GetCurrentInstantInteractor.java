package com.jonatlop.server.core.util.moment;

import com.jonatlop.server.core.abstraction.interactor.Interactor;

import javax.lang.model.type.NullType;
import java.time.Instant;

public interface GetCurrentInstantInteractor extends Interactor<NullType, Instant> {}
