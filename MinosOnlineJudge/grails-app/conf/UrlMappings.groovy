class UrlMappings {

	static mappings = {
    "/"(controller: "user")
		
    "/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		/*"/"(view:"/index")*/
		"500"(view:'/error')
	}
}
