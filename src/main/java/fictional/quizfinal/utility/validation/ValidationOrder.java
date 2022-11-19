package fictional.quizfinal.utility.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

//Note: 'javax.validation.groups.Default' group is the one used by javax validation when no group is explicitly set in the validation annotation
@GroupSequence({Default.class, ValidationStep1.class, ValidationStep2.class, ValidationStep3.class})
public interface ValidationOrder {}
