# KOD Counter

Allows to assess number of participants of a gathering using video clip

To calculate number of participants:
- Download a movie clip (e.g. from YouTube)
- Convert a movie clip into a series of frames (e.g. PNG files)
- Run KOD Counter
- Select a reference plane you will use for calculations. It has to be an object visible on all frames.
- Locate a frame where the head of the march crosses the reference plane and set is as a first data frame.
- Locate a frame where the tail of the march crosses the reference plane and set it as a last data frame.
- For some frames (the more the better) calculate number of people that crossed the reference plane.
- An application will automatically assess the total number of people

*Example 1.* A lantern is very good reference plane.

![Example 1](https://raw.githubusercontent.com/SebastianCelejewski/kod_counter/master/doc/KOD_counter_01.JPG)

*Example 2.* There is no good candidate for a reference plane. Moreover, the poor quality of the movie clip does not allow to calculate number of people that crossed the reference plane.

![Example 2](https://raw.githubusercontent.com/SebastianCelejewski/kod_counter/master/doc/KOD_counter_02.JPG)
