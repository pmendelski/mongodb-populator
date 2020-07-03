package com.coditory.sandbox.generators;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class LoremIpsumGenerator {
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent volutpat, odio at faucibus congue, tortor magna sollicitudin libero, et rhoncus nisl mauris et est. Integer vitae tortor urna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at rutrum mauris. Morbi a risus libero. Morbi sodales malesuada dui, in fringilla sem pulvinar in. Duis ac dictum arcu, sed volutpat enim. Suspendisse ac sagittis ligula. Etiam posuere purus quis erat hendrerit, et malesuada dolor consectetur. Sed nunc enim, sagittis ac neque vel, iaculis sollicitudin mi. Duis volutpat efficitur enim sed aliquam. Curabitur non dui maximus, egestas sapien vitae, facilisis dolor. Ut suscipit fermentum posuere. Integer in libero at neque aliquam commodo. Cras justo urna, malesuada id sollicitudin non, suscipit et risus. Ut suscipit in augue et pharetra. Sed pharetra nunc id erat consequat, in maximus risus efficitur. Duis et sapien neque. Nulla eros libero, fermentum non arcu id, cursus tempor turpis. Fusce lorem augue, sagittis in cursus sit amet, facilisis non massa. Nulla facilisi. Nunc a turpis nisi. Nullam quis ligula vitae urna pellentesque suscipit. Morbi elementum quam eget sollicitudin varius. Nullam ullamcorper mi nec nunc porta malesuada. Pellentesque eget sem ac nunc ultricies dapibus. Nullam vehicula, dui ut laoreet imperdiet, ligula lacus aliquam dui, quis sollicitudin dui lacus et sapien.";
    private static final String[] LOREM_IPSUM_WORDS = LOREM_IPSUM.split(" ");
    private static final Random random = new Random();

    public static String loremIpsumWords(int words) {
        return random.ints(0, LOREM_IPSUM_WORDS.length)
                .limit(words)
                .mapToObj(i -> LOREM_IPSUM_WORDS[i] + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .trim();
    }
}
