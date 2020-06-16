<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta name="robots" content="noindex, nofollow">
<title>Update Notes</title>

</head>
<body>
	<form action="NotesController" method="post">
       		
    
			<br>ID:<input type="text" class="form-control" name="notesID" value="<c:out value="${notes.notesID}" />" readonly/>
			<br>Title:<input type="text" class="form-control" name="notesTitle" value="<c:out value="${notes.notesTitle}" />" required/>	
			<br>Content:<textarea  class="form-control" id="notesContent" name="notesContent" required><c:out value="${notes.notesContent}" /></textarea>
				
						<%-- <c:out value="${notes.notesContent}" escapeXml="false" /> --%>
			
			<br><br><input type = "submit" name="action" class="btn btn-primary" value = "Update">
		</form>
		<script src="https://cdn.ckeditor.com/4.14.0/full-all/ckeditor.js"></script>		
<!-- <script src="/e-JAWI/notes/plugin.js"></script> -->
		<script>
		CKEDITOR.plugins.addExternal( 'exportpdf', '/e-JAWI/notes/node_modules/ckeditor4/plugins/exportpdf/' );
		CKEDITOR.replace( 'notesContent', {
			skin: 'kama',
			extraPlugins: 'embed,autoembed,placeholder,emoji',
			width: 940,
		      height: 700,

		      // Load the default contents.css file plus customizations for this sample.
		      
		      // Setup content provider. See https://ckeditor.com/docs/ckeditor4/latest/features/media_embed
		      embed_provider: '//ckeditor.iframe.ly/api/oembed?url={url}&callback={callback}',

		      // Configure the Enhanced Image plugin to use classes instead of styles and to disable the
		      // resizer (because image size is controlled by widget styles or the image takes maximum
		      // 100% of the editor width).
		      /* image2_alignClasses: ['image-align-left', 'image-align-center', 'image-align-right'],
		      image2_disableResizer: true */
		});
		
                        
                </script>
                
</body>
</html>