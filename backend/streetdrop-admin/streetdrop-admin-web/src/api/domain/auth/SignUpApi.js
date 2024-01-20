import {axiosAuthInstance} from "../../AxiosInstance";

const SignUpApi = {
    signUp: (username, email, part, name, password) => {
        const data = {
            username: username,
            email: email,
            part: part,
            name: name,
            password: password
        }

        return axiosAuthInstance.post('/sign-up', data);
    }
}

export default SignUpApi;