import { configureStore } from "@reduxjs/toolkit";

import userReducer from './usersDataStore';
import errorsReducer from './errorsStore';

const store = configureStore({
    reducer: {
        USERS_DATA_SLICES_NAME: userReducer,
        ERRORS_SLICES_NAME: errorsReducer,
    }
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;