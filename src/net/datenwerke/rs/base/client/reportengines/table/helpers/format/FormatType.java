package net.datenwerke.rs.base.client.reportengines.table.helpers.format;

import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;

public enum FormatType {

	DEFAULT {
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeDefault();
		}
	},
	NUMBER{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeNumber();
		}
	},
	CURRENCY{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeCurrency();
		}
	},
	DATE{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeDate();
		}
	},
	PERCENT{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypePercent();
		}
	},
	SCIENTIFIC{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeScientific();
		}
	},
	TEXT{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeText();
		}
	},
	TEMPLATE{
		@Override
		public String toString() {
			return FilterMessages.INSTANCE.formatTypeTemplate();
		}
	}
}
