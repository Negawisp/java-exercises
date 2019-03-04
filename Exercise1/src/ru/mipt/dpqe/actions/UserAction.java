package ru.mipt.dpqe.actions;

import ru.mipt.dpqe.session.UserSession;

/**
 * Created by krm on 03.04.2017.
 */

// public final interface UserAction {                  // No point in final interfaces
public interface UserAction {
    void doAction(UserSession session);
}
