package zodiac.builder.update;

import zodiac.builder.Builder;
import zodiac.builder.LogicalOperatorsProvider;
import zodiac.builder.WhereClauseProvider;

interface BuilderLogicalOperatorsProvider extends LogicalOperatorsProvider<WhereClauseProvider<?>>, Builder {
}
