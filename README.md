### Java tool to convert from FLAC to MP3

#### Build

`./gradlew jar`

creates a fat jar

#### Usage

`java -jar <path_to_jar> [path_to_flacs]`

Converts all flac files recursively found in the path specified to mp3. The new mp3 files are saved alongside with original flac files.

`path_to_flacs` can be a path to a single flac or a folder. If not specified then default value is used, which is current working directory.

This tool uses JAVE2, a java library which is a wrapper around ffmpeg.
JAVE2 home page: https://github.com/a-schild/jave2
