//package mk.ukim.finki.wp.lab1.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab1.model.Artist;
//import mk.ukim.finki.wp.lab1.model.Song;
//import mk.ukim.finki.wp.lab1.service.implementation.SongServiceImpl;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "SongListServlet", urlPatterns = "/song/song-list")
//public class SongListServlet extends HttpServlet {
//
//    private final SpringTemplateEngine templateEngine;
//    private final SongServiceImpl songService;
//
//    public SongListServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService) {
//        this.templateEngine = templateEngine;
//        this.songService = songService;
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
//        List<Song> songList = songService.listSongs();
//
//        String search = req.getParameter("search");
//
//        if (search!=null){
//            System.out.println(search);
//            //songList = songList.stream().filter(i -> i.getTitle().toLowerCase().contains(search.toLowerCase())).toList();
//            songList = songService.findByTitle(search);
//        }
//
//        context.setVariable("songList", songList);
//
//        templateEngine.process("listSongs.html", context, resp.getWriter());
//    }
//}
