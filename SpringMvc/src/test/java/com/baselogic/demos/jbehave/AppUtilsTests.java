package com.baselogic.demos.jbehave;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
//import org.jbehave.examples.trader.service.TradingService;
// import org.jbehave.examples.trader.steps.AndSteps;
// import org.jbehave.examples.trader.steps.BeforeAfterSteps;
// import org.jbehave.examples.trader.steps.CalendarSteps;
// import org.jbehave.examples.trader.steps.CompositeNestedSteps;
// import org.jbehave.examples.trader.steps.CompositeSteps;
// import org.jbehave.examples.trader.steps.MetaParametrisationSteps;
// import org.jbehave.examples.trader.steps.NamedParametersSteps;
// import org.jbehave.examples.trader.steps.ParameterDelimitersSteps;
// import org.jbehave.examples.trader.steps.ParametrisedSteps;
// import org.jbehave.examples.trader.steps.PendingSteps;
// import org.jbehave.examples.trader.steps.PriorityMatchingSteps;
// import org.jbehave.examples.trader.steps.SandpitSteps;
// import org.jbehave.examples.trader.steps.SearchSteps;
// import org.jbehave.examples.trader.steps.TraderSteps;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import com.baselogic.demos.domain.Customer;
import com.baselogic.demos.domain.Item;
import com.baselogic.demos.domain.ShoppingCart;
import com.baselogic.demos.jbehave.steps.AppUtilsSteps;
import com.baselogic.demos.jbehave.steps.ShoppingCartSteps;

public class AppUtilsTests extends JUnitStory {

	private final CrossReference xref = new CrossReference();

	public AppUtilsTests() {
		configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
				.useThreads(2).useStoryTimeoutInSecs(60);
		// Uncomment to set meta filter, which can also be set via Ant or Maven
		// configuredEmbedder().useMetaFilters(Arrays.asList("+theme parametrisation"));
	}

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");
		// Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		// factory to allow parameter conversion and loading from external
		// resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
				new LocalizedKeywords(),
				new LoadFromClasspath(embeddableClass), parameterConverters);
		// add custom converters
		parameterConverters.addConverters(new DateConverter(
				new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory));

		return new MostUsefulConfiguration()
				.useStoryControls(
						new StoryControls().doDryRun(false)
								.doSkipScenariosAfterFailure(false))
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryParser(new RegexStoryParser(examplesTableFactory))
				.useStoryPathResolver(new UnderscoredCamelCaseResolver())
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(
										CodeLocations
												.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withPathResolver(new ResolveToPackagedName())
								.withViewResources(viewResources)
								.withFormats(CONSOLE, TXT, HTML, XML)
								.withFailureTrace(true)
								.withFailureTraceCompression(true)
								.withCrossReference(xref))
				.useParameterConverters(parameterConverters)
				// use '%' instead of '$' to identify parameters
				.useStepPatternParser(
						new RegexPrefixCapturingPatternParser("%"))
				.useStepMonitor(xref.getStepMonitor());
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new AppUtilsSteps());
	}
}