import http from "../http-common.js";

class LoginService {
  login(data) {
    return http.post("/login", data);
  }
  register(newStudentData) {
    return http.post("/register", newStudentData);
  }

}

export default new LoginService();
