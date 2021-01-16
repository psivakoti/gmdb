# The G Movie Database

Fans are crazy for movies and the G company would like to build an application for users to browse all the movies of the world!

## API Specifications



**Resource Summary**

| URI                                      | HTTP Method |   HTTP Status   | Description                              |
|------------------------------------------|-------------|-----------------|------------------------------------------|
| gmdb.com/api/movies                      | GET         |    200 OK       | Return a list of movies.                 |
| gmdb.com/api/movies                      | POST        |    201 CREATED  | Form submission to add a new movie.       
| gmdb.com/api/movies/{title}               | GET         |    304 FOUND and 404 NOT FOUND | Return a movie by title else throw Not Found message.

---

**GET /api/movies**

Response Body:
```json
[
  {
    "title": "The Avengers",
    "director": "Joss Whedon",
    "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
    "release": "2012",
    "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
    "rating": null
  },
  {
    "title": "Superman Returns",
    "director": "Bryan Singer",
    "actors": "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden",
    "release": "2006",
    "description": "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.",
    "rating": null
  }
]
```
---

**POST /api/movies**

Request Body:
```json

  {
    "title": "The Avengers",
    "director": "Joss Whedon",
    "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
    "release": "2012",
    "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
    "rating": null
  }

```

Response Body:
```
201 Created
```

**GET /api/movies/{title}**

Response Body_304 Found::


```json

  {
    "title": "The Avengers",
    "director": "Joss Whedon",
    "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
    "release": "2012",
    "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
    "rating": null
  }

```

Response Body_404 Movie not found:
```
```



