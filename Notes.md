- Return String
```java
@GetMapping("/")
  public String root() {
    return "Hello";
  }
```

- Return JSON
```java
@GetMapping("/")
public Map<String, String> root() {
    return Map.of("message", "Hello world");
}
```

- Query parameter in GET
```java
@GetMapping("/")
public String root(@RequestParam String name) {
    return "Hello, " + name;
}
```

- GET and POST on same route
```java
@GetMapping("/")
public String getRoot() {
    return "GET /";
}

@PostMapping("/")
public String postRoot() {
    return "POST /";
}
```

- Conditional with header params
```java
@GetMapping(value = "/", params = "type=basic")
public String basicType() {
    return "Basic Type";
}

@GetMapping(value = "/", params = "type=advanced")
public String advancedType() {
    return "Advanced Type";
}
```

- Same method for multiple paths
```java
@GetMapping({"/", "/home", "/start"})
public String rootAliases() {
    return "Hello world";
}
```

- Custom status code
```java
@GetMapping("/")
public ResponseEntity<String> root() {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body("Hello with status 202");
}
```

- Header
```java
@GetMapping("/secret")
public String secret(@RequestHeader("X-Auth-Token") String token) {
    if ("abc123".equals(token)) {
        return "Access granted";
    } else {
        return "Access denied";
    }
}
```

- Optional header
```java
@GetMapping("/secret")
public String secret(@RequestHeader("X-Auth-Token") String token) {
    if ("abc123".equals(token)) {
        return "Access granted";
    } else {
        return "Access denied";
    }
}
```