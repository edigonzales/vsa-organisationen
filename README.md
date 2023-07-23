# vsa-organisationen

## todo
- Sortieren: Berücksichtigen Umlaute
- Datum als Typ?
- Pageing
- Filtern / Suchen
- Excel
- Dialog mit XTF-Objekt?

## XTF in Datenbank importieren

```
java -jar /Users/stefan/apps/ili2gpkg-4.9.0-aarch64/ili2gpkg-4.9.0.jar --dbfile vsa-organisationen.gpkg --defaultSrsCode 2056 --createEnumTabs --createFk --createFkIdx --createUnique --createNumChecks --createTextChecks --createDateTimeChecks --createMandatoryChecks --createImportTabs --models SIA405_Base_Abwasser_LV95 --doSchemaImport --import /Users/stefan/Downloads/vsa_organisationen.xtf
```

## Cayenneklassen herstellen

```
./mvnw cayenne:cdbimport
```

```
./mvnw cayenne:cgen
```

Ggf die beiden XML-Dateien löschen, falls man nichts an der DB geändert hat, sondern nur in der Konfiguration im Maven-Plugin.

## Entwicklung

### Run 

```
./mvnw -Dspring-boot.run.profiles=dev
```

