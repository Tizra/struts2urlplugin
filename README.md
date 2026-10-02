# Struts2 url plugin prerelease

([Official project page](https://code.google.com/p/struts2urlplugin/))

This is a git fork of the original project, which does not seem
to be active (no changes since 2008). It's been useful to me,
but as I've had to fix some issues, I've forked it onto github.
The project page says Apache 2.0 license, so I have added license and copyright
notices to the repo, since they were not in the SVN tree I originally
checked out.

This builds on jitpack.io, for example:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.tizra</groupId>
    <artifactId>struts2urlplugin</artifactId>
    <version>0.1-tizra.20</version>
</dependency>
```

Note that you have to create a tag 0.1-tizra.21 (or whatever, to name the version of the resulting build.)

In order to trigger Jitpack to build the resulting artifact from the tag, change your project that
depends on this to include your newly-pushed tag as its dependency version.  When you run
`mvn clean package` when Maven tries to download the artifact, Jitpack will build it on demand.

*Note*: as of October 2026, Jitpack.io uses Maven 3.2.5. We cannot upgrade any Maven plugins in this build
until Jitpack uses a newer version of Maven (or we change the build tool).
