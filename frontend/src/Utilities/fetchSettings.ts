import axios, { type AxiosResponse } from "axios";
import { setErrors, clearErrors } from "../Store/errorsStore";
import { type AppStore } from "../Store/store";
import type { GlobalErrorState } from "./types";


export const axiosAuth = axios.create({
    withCredentials: true,
});

export const setUpInterceptors = (store: AppStore) => {
    axiosAuth.interceptors.response.use(
        (response: AxiosResponse) => {
            store.dispatch(clearErrors());
            return response;
        },

        (error) => {
            if(axios.isAxiosError(error)){
                const errorObj: GlobalErrorState = {
                    status: error.status!,
                    message: error.message!,
                }

                store.dispatch(setErrors(errorObj));
                return Promise.resolve(errorObj);

            } else{
                const errorObj: GlobalErrorState = {
                    status: null,
                    message: 'Some error not AXIOS'
                }

                store.dispatch(setErrors(errorObj));
                return Promise.resolve(errorObj);
            }
        }
    );
}
