package com.wooruk.controller.user;

import com.wooruk.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userFindAccountController", urlPatterns = {"/user/idquery"})
public class UserFindAccountController extends HttpServlet {


    private final Logger log = LoggerFactory.getLogger(UserFindAccountController.class);
    private final UserService userService = UserService.getInstance();

    public static String getRequestBody(HttpServletRequest request) throws IOException {

        String reqStr = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        reqStr = stringBuilder.toString();
        return reqStr;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.debug("doGet::");
        req.getRequestDispatcher("/WEB-INF/user/find-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.debug("doPost:bibi:");
        String reqBody = getRequestBody(req);
        JSONObject data = new JSONObject(reqBody);
        String userIdOfDb = "";
        log.debug("reqBody={}", reqBody);

        Integer type = data.getInt("type");
        String email = data.getString("email");
        String userId = null;

        if (type == 2) {
            userId = data.getString("userId");
        }

        PrintWriter out = resp.getWriter();

        if (type == 1) {
            userIdOfDb = userService.getUserIdByEmail(email);
            out.print(userIdOfDb);
        } else {
            boolean result = userService.getUserIdByIdAndEmail(email, userId);
            out.print(result);
        }

    }
}
