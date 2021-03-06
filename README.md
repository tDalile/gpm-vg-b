<!-- TABLE OF CONTENTS -->
<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#über-das-projekt">Über das Projekt</a>
      <ul>
        <li><a href="#verwendete-technologien">Verwendete Technologien</a></li>
      </ul>
    </li>
    <li>
      <a href="#erste-schritte">Erste Schritte</a>
      <ul>
        <li><a href="#voraussetzungen">Voraussetzungen</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
      <a href="#verwendung-der-applikation">Verwendung der Applikation</a>
    </li>
    <li>
      <a href="#beispielprozess">Beispielprozess</a>
    </li>
    <li>
      <a href="#weitere-informationen">Weitere Informationen</a>
    </li>
  </ol>
</details>

<!-- PROJECT LOGO -->
<br />
<p align="center">

  <a href="https://github.com/tDalile/gpm-vg-b">
    <img src="images/url.png" alt="Logo" width="240" height="240"><br>
    <a href="https://github.com/tDalile/gpm-vg-b">Repository auf Github</a>
  </a>

<!-- ABOUT THE PROJECT -->
## Über das Projekt

Dies ist ein studentisches Projekt an der TH Köln für das Modul Geschäftsprozessmanagement.

**Projektauftrag:**

Sie arbeiten in der IT Abteilung der Versicherungsgesellschaft VG AG. Die VG
AG bietet Krankenversicherungen für Privatpersonen an.
Ihr Unternehmen hat Sie als Projektteam mit der Implementierung/Automatisie-
rung des Antragsbearbeitungsprozesses basierend auf einem Workflow Ma-
nagement System (WFMS) beauftragt.

### Verwendete Technologien

Folgende Technologien wurden zur Umsetzung des Projektauftrages verwendet.

- [Camunda](https://camunda.com/download/)
- [PostgreSQL](https://www.postgresql.org/)
- [Tomcat](https://tomcat.apache.org/)
- [Docker](https://www.docker.com/)

<!-- GETTING STARTED -->

## Erste Schritte

Dank der Verwendung von Docker in diesem Projekt, kann die Applikation mit nur einem Befehl gestartet werden. Für den Fall das nichtsdestotrotz die WAR Datei benötigt werden sollte, folgt auch für diese (etwas kompliziertere) Vorgehensweise die Installationsanleitung.

### Download des Source-Codes

Optionen:

**Klonen des Repositories:**

```sh
git clone https://github.com/tDalile/gpm-vg-b.git
```

**oder .zip-Datei herunterladen und entpacken:**

[Zip-Datei herunterladen](https://github.com/tDalile/gpm-vg-b/archive/main.zip)

### Voraussetzungen

Für die Installation wird Docker benötigt. Eine ausführliche Installationsanleitung für Docker ist hier zu finden: [Install Docker](https://docs.docker.com/docker-for-windows/install/)

> _Hinweis: Für die Installationsmöglichkeit mithilfe von [Maven](https://maven.apache.org/install.html) muss Maven auch installiert werden! Bei der Installation mittels Docker muss Maven nicht zusätlich installiert werden._

### Installation

**Installation via Docker** (einfach)

Nachdem Docker erfolgreich installiert wurde, bitte folgenden Befehl in dem Projektverzeichnis per CLI ausführen:

```sh
docker-compose up -d
```

Sobald Docker alle Dependencies heruntergeladen und den Build-Prozess abgeschlossen hat, kann mit dem Schritt [Verwendung der Applikation](#verwendung-der-applikation) fortgefahren werden.

**Installation via Maven** (komplizierter und benötigt trotzdem Docker)

> _*Erklärung*: Da dieses Projekt Kundendaten nutzt und dabei eine Postgresql Datenbank innerhalb eines Docker-Containers verwendet, muss der entsprechende Container vor dem Starten der Camunda Plattform bereits laufen!_

Nachdem Docker **und** Maven erfolgreich installiert wurden, bitte folgende Befehle in dem Projektverzeichnis per CLI ausführen.

1. Datenbank-Container starten:

```sh
docker-compose up -d psql
```

2. Maven Build:

```sh
mvn clean compile package
```

3. Maven erzeugt die Datei *vgb.war* und legt diese im Ordner *./target* ab. Dieser Ordner muss in den Deployment Ordner *tomcat/webapps* des Webservers kopiert werden.

## Verwendung der Applikation

Nachdem die Anwendung erfolgreich installiert und gestartet wurde, müssen folgende Schritte ausgeführt werden um diese zu verwenden.

1. Öffnen der Weboberfläche:
   [Camunda Weboberfläche](http://localhost:8080/camunda/app/welcome/default/#!/login)

2. Login mit Nutzerdaten

Verwendbare Nutzerdaten:
| Username | Password | Funktion | Zugriff auf Tasklist? | Zugriff auf Cockpit? | Zugriff auf Adminpanel? |
|---|---|---|---|---|---|
| demo | demo | Admin, kann alles, darf alles | <ul><li>- [x] </li> |  <ul><li>- [x] </li> |  <ul><li>- [x] </li>
| customer | customer | Kunde, kann Prozessinstanzen starten |  <ul><li>- [x] </li> |  <ul><li>- [ ] </li> |  <ul><li>- [ ] </li> |
| clerk | clerk | Sachbearbeiter, bearbeitet Anträge des Kunden |  <ul><li>- [x] </li> |  <ul><li>- [ ] </li> |  <ul><li>- [ ] </li> |
| head | head | Abteilungsleiter, entscheidet bei Sonderfällen |  <ul><li>- [x] </li> |  <ul><li>- [ ] </li> |  <ul><li>- [ ] </li> |

3. Starten einer Prozessinstanz mit dem Kundenkonto

## Beispielprozess

**Prozessmodell:**
![BPMN-Modell](images/antragsbearbeitungsprozess.png)

Alle Modelle (BPMN und DMN) sind hier zu finden: [Modelle](src/main/resources)

Ist alles gestartet, kann ein Probedurchlauf mit den oben genannten Nutzern durchgeführt werden. Ein Beispiel hierfür kann im folgendem Video eingesehen werden: [Ilias-Video](https://ilias.th-koeln.de/goto.php?target=file_1823708_download&client_id=ILIAS_FH_Koeln).

Für abweichende Ergebnisse können die Daten des Kunden entsprechend der Aufgabenstellung angepasst werden. Bspw. Premiumtarif und alle Vorerkranungen ergibt eine automatische Ablehnung. Der Sachbearbeiter muss in diesem Fall nur die Ablehnungsbegründung hinzufügen.

## Weitere Informationen

**Nützliche Befehle**
| Funktion | Befehl |
|---|---|
| Logs der Container anzeigen lassen | ```docker logs --follow [psql \| camunda] ```|
| Neustart der Container | ```docker-compose up --build``` |
| Container stoppen | ```docker-compose down``` |
| Container Status überprüfen | ```docker ps``` |
