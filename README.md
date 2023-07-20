# vsa-organisationen

## XTF in Datenbank importieren

```
java -jar /Users/stefan/apps/ili2gpkg-4.9.0-aarch64/ili2gpkg-4.9.0.jar --dbfile vsa-organisationen.gpkg --defaultSrsCode 2056 --createEnumTabs --createFk --createFkIdx --createUnique --createNumChecks --createTextChecks --createDateTimeChecks --createMandatoryChecks --createImportTabs --models SIA405_Base_Abwasser_LV95 --doSchemaImport --import /Users/stefan/Downloads/vsa_organisationen.xtf
```