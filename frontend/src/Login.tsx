import { GoogleLogin, type CredentialResponse } from "@react-oauth/google";
import { useDispatch } from "react-redux";
import { useMutation } from "@tanstack/react-query";
import { jwtDecode } from "jwt-decode";

import LogInUser from "./APIs/logInUser";

type UsersInfo = {
    email: string,
    name: string,
}

function LogIn()
{
    const { mutate, isError, error} = useMutation({
        mutationFn: async (obj: CredentialResponse) => {
            const decodedObject: any = jwtDecode(obj.credential!);
            const usersInfo: Pick<typeof decodedObject, "email" | "name"> = {
                email: decodedObject.email!,
                name: decodedObject.name!,
            }
            
            const data = await LogInUser(usersInfo);
            console.table(data);
        }
    });

    return (
        <div>
            {isError && <div>{error.message}</div>}
            <GoogleLogin onSuccess={(e) => mutate(e) }></GoogleLogin>
        </div>)
};

export default LogIn;