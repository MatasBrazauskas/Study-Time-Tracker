import { useQuery} from "@tanstack/react-query";
import { useDispatch, useSelector } from "react-redux";
import type { RootState } from "./Store/store";

import { getUsersProfile } from "./APIs/userProfileAPIs";
import { USER_PROFILE_QUERY_KEY } from "./Utilities/const";
import { setUsersInfo } from "./Store/usersStore";
import { useNavigate } from "react-router-dom";

function TopBar(){

    const usersProfile = useSelector((state: RootState) => state.USERS_DATA_SLICES_NAME);
    const errors = useSelector((state: RootState) => state.ERRORS_SLICES_NAME);

    const dispatch = useDispatch();
    const navigator = useNavigate();

    useQuery({
        queryFn: async() => {
            const usersData = await getUsersProfile();
            dispatch(setUsersInfo(usersData));
            return usersData;
        },
        queryKey: [USER_PROFILE_QUERY_KEY],
        staleTime: Infinity,
    });

    return (
        <div>
            {Object.entries(usersProfile).map((value, i) => {
                return <div key={i}>{value[0]}: {value[1]}</div>
            })}

            {Object.entries(errors).map((value, i) => {
                return <div key={i}>{value[0]}: {value[1]}</div>
            })}

            <button onClick={() => navigator('/login')}>Log In</button>
            <button onClick={() => navigator('/')}>Back To Main</button>
        </div>
    )
}

export default TopBar;