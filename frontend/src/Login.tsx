import { GoogleLogin, type CredentialResponse } from "@react-oauth/google";
import { useDispatch } from "react-redux";
import { useMutation } from "@tanstack/react-query";


function LogIn()
{
    const dispatch = useDispatch();

    const { mutate, isError, error} = useMutation({
        mutationFn: async (obj: CredentialResponse) => {
            console.table(obj);
        }
    });

    return (
        <div>
            {isError && <div>{error.message}</div>}
            <GoogleLogin onSuccess={(e) => mutate(e) }></GoogleLogin>
        </div>)
};

export default LogIn;