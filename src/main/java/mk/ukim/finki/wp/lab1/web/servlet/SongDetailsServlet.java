//package mk.ukim.finki.wp.lab1.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab1.model.Artist;
//import mk.ukim.finki.wp.lab1.model.Song;
//import mk.ukim.finki.wp.lab1.service.implementation.ArtistServiceImpl;
//import mk.ukim.finki.wp.lab1.service.implementation.SongServiceImpl;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "SongDetailsServlet", urlPatterns = "/song/song-details")
//public class SongDetailsServlet extends HttpServlet {
//
//    private final SpringTemplateEngine templateEngine;
//    private final SongServiceImpl songService;
//    private final ArtistServiceImpl artistService;
//
//    public SongDetailsServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
//        this.templateEngine = templateEngine;
//        this.songService = songService;
//        this.artistService = artistService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//
//        Song s = songService.listSongs().stream().findFirst().orElse(null);
//
//        context.setVariable("song", s);
//        templateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//
//        String trackId = req.getParameter("trackId");
//        String artistId = req.getParameter("artistId");
//        Song s = songService.listSongs().stream().findFirst().orElse(null);
//
//        if (trackId != null && artistId != null) {
//            s = songService.findByTrackId(trackId);
//            Artist a = artistService.findById(Long.valueOf(artistId));
//
//            if (!s.getPerformers().contains(a)){
//                s.addPerformer(a);
//            }
//        }
//
//        context.setVariable("song", s);
//        templateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//}
