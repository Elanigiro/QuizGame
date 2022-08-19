package fictional.quizfinal.utility.validation;

import javax.validation.GroupSequence;

@GroupSequence({ValidationStep1.class, ValidationStep2.class, ValidationStep3.class})
public interface ValidationOrder {}
