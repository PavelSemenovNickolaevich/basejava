package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactsType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    //    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "    <title>Список всех резюме</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<section>\n" +
                        "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
                        "    <tr>\n" +
                        "        <th>Имя</th>\n" +
                        "        <th>Email</th>\n" +
                        "    </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
                            "     <td>" + resume.getContact(ContactsType.MAIL) + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
