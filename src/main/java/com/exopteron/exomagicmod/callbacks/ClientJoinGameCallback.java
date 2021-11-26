package com.exopteron.exomagicmod.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ClientJoinGameCallback {
    Event<ClientJoinGameCallback> EVENT = EventFactory.createArrayBacked(ClientJoinGameCallback.class, (listeners) -> () -> {
        for (ClientJoinGameCallback listener : listeners) {
            listener.call();
        }
    });
    void call();
}
