package com.lab.HibernateValidator.CustomBundle;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import com.lab.HibernateValidator.Basic.User;

public class Application {

	public static void main(String[] args) {

		/*
		 * By default, framework picks up validation messages from
		 * ValidationMessages.properties file in classpath. You may configure your own
		 * custom property files as below.
		 * 
		 * Put these two files in classpath i.e. messages.properties and
		 * otherMessages.properties. Now all messages will be resolved from these two
		 * property files.
		 * 
		 */

		Validator validator = Validation.byDefaultProvider().configure()
				.messageInterpolator(new ResourceBundleMessageInterpolator(
						new AggregateResourceBundleLocator(Arrays.asList("messages", "otherMessages"))))
				.buildValidatorFactory().getValidator();

		User user = new User(null, "Hari", "hari.r@isteer.com");

		// Validate bean
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		// Show errors
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<User> violation : constraintViolations) {
				System.out.println(violation.getMessage());
			}
		} else {
			System.out.println("Valid Object");
		}
	}
}
