package servlets;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.paloit.dao.NewsService;
import com.paloit.entities.News;
import com.paloit.manager.NewsManager;

/**
 * The Image servlet for serving from database.
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
public class ImageServlet extends HttpServlet {

    // Constants ----------------------------------------------------------------------------------

    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    // Statics ------------------------------------------------------------------------------------

    private  NewsManager manager;
    
    @Autowired
    public void setManager(NewsManager manager) {
        this.manager = manager;
    }

    // Actions ------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // Get ID from request.
    
        String imageId = request.getParameter("idNews");
        System.out.println(imageId);

        // Check if ID is supplied to the request.
       if (imageId == null) {
            // Do your thing if the ID is not supplied to the request.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Lookup Image by ImageId in database.
        // Do your "SELECT * FROM Image WHERE ImageID" thing.
        News news = new News();
        
        news = manager.recupNewsId(Integer.parseInt("idNews"));
        System.out.println(news.getTitreNews());
        

        // Check if image is actually retrieved from database.
 /*       if (news == null) {
            // Do your thing if the image does not exist in database.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }*/

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentLength(news.getImageNews().length);
        response.setHeader("Content-Disposition", "inline; filename=\"" + news.getTitreNews() + "\"");

        // Prepare streams.
        BufferedOutputStream output = null;

        try {
            // Open streams.
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            output.write(news.getImageNews());
        } finally {
            // Gently close streams.
            close(output);
        }
    }

    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
	    

	    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	


}
