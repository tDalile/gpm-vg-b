
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
      <a href="#weitere-informationen">Weitere Informationen</a>
    </li>
  </ol>
</details>

<!-- PROJECT LOGO -->
<br />
<p align="center">

  <a href="https://github.com/tDalile/gpm-vg-b">
    <img src="images/url.png" alt="Logo" width="160" height="160"><br>
    <a href="https://github.com/tDalile/gpm-vg-b">Repository auf Github</a>
  </a>

<!-- ABOUT THE PROJECT -->
## Über das Projekt

Sie arbeiten in der IT Abteilung der Versicherungsgesellschaft VG AG. Die VG
AG bietet Krankenversicherungen für Privatpersonen an.
Ihr Unternehmen hat Sie als Projektteam mit der Implementierung/Automatisie-
rung des Antragsbearbeitungsprozesses basierend auf einem Workflow Ma-
nagement System (WFMS) beauftragt.

### Verwendete Technologien

Folgende Technologien wurden zur Umsetzung des Projektauftrages verwendet.

* [Camunda](https://camunda.com/download/)
* [PostgreSQL](https://www.postgresql.org/)
* [Tomcat](https://tomcat.apache.org/)
* [Docker](https://www.docker.com/)

<!-- GETTING STARTED -->
## Erste Schritte

Dank der Verwendung von Docker in diesem Projekt, kann die Applikation mit nur einem Befehl gestartet werden. Für den Fall das nichtsdestotrotz die WAR Datei benötigt werden sollte, folgt auch für diese (etwas kompliziertere) Vorgehensweise die Installationsanleitung.

### Download des Source-Codes:

Optionen:

**Klonen des Repositories:**

```sh
    git clone https://github.com/tDalile/gpm-vg-b.git
```

**oder .zip-Datei herunterladen und entpacken:**

[Zip-Datei herunterladen](https://github.com/tDalile/gpm-vg-b/archive/main.zip)

### Voraussetzungen

Für die Installation wird Docker benötigt. Eine ausführliche Installationsanleitung für Docker ist hier zu finden: [Install Docker](https://docs.docker.com/docker-for-windows/install/)

>*Hinweis: Für die Installationsmöglichkeit mithilfe von [Maven](https://maven.apache.org/install.html) muss Maven auch installiert werden! Bei der Installation mittels Docker muss Maven nicht zusätlich installiert werden*

### Installation

**Installation via Docker** (einfach)

1. Welche Archivdateien sind in Eclipse wie zu importieren?
2. Wie ist die Camunda-Umgebung (insbesondere Benutzerrechte) zu konfigurieren?
3. Was genau ist zu tun, um den Prozess auszuführen?
4. Mit welchen Eingaben ist ein Probedurchlauf auszuführen und mit welchen
Ergebnissen ist zu rechnen?

**Installation via Maven** (komplizierter und benötigt trotzdem Docker)

## Verwendung der Applikation

Nachdem die Anwendung erfolgreich installiert und gestartet wurde, müssen folgende Schritte ausgeführt werden um diese zu verwenden.

1. Öffnen der Weboberfläche:
[Camunda Weboberfläche](http://localhost:8080/camunda/app/welcome/default/#!/login)

## Weitere Informationen

* Logs der Container anzeigen lassen
* Neustart
* Beenden
* Laufen die Container?
