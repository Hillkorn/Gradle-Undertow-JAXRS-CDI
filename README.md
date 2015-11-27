# Undertow Resteasy with CDI

## deltaspike branch
Conatins working gradle setup that starts an undertow server with resteasy and deltaspike (using openwebbeans instead of weld).

## master branch
Also a gradle setup with undertow and resteasy but direct use of weld.

## Problems i had
### @Inject didn't worked
You need to have resources and classes in the same dir to let cdi detect your classes.
```gradle
sourceSets {
  main {
    output.resourcesDir = 'build/classes/main'
    output.classesDir   = 'build/classes/main'
  }
}
```
