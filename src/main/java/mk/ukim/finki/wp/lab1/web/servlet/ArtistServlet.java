//package mk.ukim.finki.wp.lab1.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab1.model.Artist;
//import mk.ukim.finki.wp.lab1.service.implementation.ArtistServiceImpl;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "ArtistServlet", urlPatterns = {"/artist"})
//public class ArtistServlet extends HttpServlet {
//
//    private final SpringTemplateEngine templateEngine;
//    private final ArtistServiceImpl artistService;
//
//    public ArtistServlet(SpringTemplateEngine templateEngine, ArtistServiceImpl artistService) {
//        this.templateEngine = templateEngine;
//        this.artistService = artistService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange iWebExchange = JakartaServletWebApplication
//                .buildApplication(req.getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//
//        List<Artist> artistList = artistService.listArtists();
//
//        context.setVariable("artistList", artistList);
//        templateEngine.process("artistsList.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange iWebExchange = JakartaServletWebApplication
//                .buildApplication(req.getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//
//        List<Artist> artistList = artistService.listArtists();
//        String songRadio = req.getParameter("songRadio");
//
//        if (songRadio == null) {
//            songRadio = "?";
//        }
//
//        context.setVariable("trackId", songRadio);
//        context.setVariable("artistList", artistList);
//        templateEngine.process("artistsList.html", context, resp.getWriter());
//    }
//}