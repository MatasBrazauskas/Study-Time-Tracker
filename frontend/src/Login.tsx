import { GoogleLogin, type CredentialResponse } from "@react-oauth/google";
import { useDispatch } from "react-redux";
import { useMutation } from "@tanstack/react-query";
import { jwtDecode } from "jwt-decode";

import { LogInUser, RegisterUser } from "./APIs/logInRegister";

function LogIn()
{
    /*const { mutate, isError, error} = useMutation({
        mutationFn: async (obj: CredentialResponse) => {
            const decodedObject: any = jwtDecode(obj.credential!);
            const usersInfo: Pick<typeof decodedObject, "email" | "name"> = {
                email: decodedObject.email!,
                name: decodedObject.name!,
            }
            
            const data = await LogInUser(usersInfo);
            console.table(data);
        }
    });*/

    const logIn = async (obj: CredentialResponse) => {
        const decodedObject: any = jwtDecode(obj.credential!);
        const usersInfo: Pick<typeof decodedObject, "email" | "username"> = {
            email: decodedObject.email!,
            username: decodedObject.name!,
        }
        
        const data = await LogInUser(usersInfo);
        console.warn('Registering');
        console.table(data);
    }

    const register = async (obj: CredentialResponse) => {
        const decodedObject: any = jwtDecode(obj.credential!);
        const usersInfo: Pick<typeof decodedObject, "email" | "username"> = {
            email: decodedObject.email!,
            username: decodedObject.name!,
        }
        
        const data = await RegisterUser(usersInfo);
        console.warn('Loggin in');
        console.table(data);
    }

    return (
        <div>
            {/*isError && <div>{error.message}</div>*/}
            <GoogleLogin onSuccess={(e) => logIn(e)}></GoogleLogin>
            <GoogleLogin onSuccess={(e) => register(e)}></GoogleLogin>
        </div>)
};

export default LogIn;