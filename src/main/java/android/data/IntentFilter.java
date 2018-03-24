package android.data;

import java.util.ArrayList;

public class IntentFilter {
	public static class Data {
		public String scheme;
		public String host;
		public String port;
		public String path;
		public String pathPattern;
		public String pathPrefix;
		public String mimeType;

		public String toString() {
			StringBuilder builder = new StringBuilder();

			builder.append(String.format("%s: %s\n", "scheme", scheme));
			builder.append(String.format("%s: %s\n", "host", host));
			builder.append(String.format("%s: %s\n", "port", port));
			builder.append(String.format("%s: %s\n", "path", path));
			builder.append(String
					.format("%s: %s\n", "pathPattern", pathPattern));
			builder.append(String.format("%s: %s\n", "pathPrefix", pathPrefix));
			builder.append(String.format("%s: %s\n", "mimeType", mimeType));

			return builder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((host == null) ? 0 : host.hashCode());
			result = prime * result
					+ ((mimeType == null) ? 0 : mimeType.hashCode());
			result = prime * result + ((path == null) ? 0 : path.hashCode());
			result = prime * result
					+ ((pathPattern == null) ? 0 : pathPattern.hashCode());
			result = prime * result
					+ ((pathPrefix == null) ? 0 : pathPrefix.hashCode());
			result = prime * result + ((port == null) ? 0 : port.hashCode());
			result = prime * result
					+ ((scheme == null) ? 0 : scheme.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Data other = (Data) obj;
			if (host == null) {
				if (other.host != null)
					return false;
			} else if (!host.equals(other.host))
				return false;
			if (mimeType == null) {
				if (other.mimeType != null)
					return false;
			} else if (!mimeType.equals(other.mimeType))
				return false;
			if (path == null) {
				if (other.path != null)
					return false;
			} else if (!path.equals(other.path))
				return false;
			if (pathPattern == null) {
				if (other.pathPattern != null)
					return false;
			} else if (!pathPattern.equals(other.pathPattern))
				return false;
			if (pathPrefix == null) {
				if (other.pathPrefix != null)
					return false;
			} else if (!pathPrefix.equals(other.pathPrefix))
				return false;
			if (port == null) {
				if (other.port != null)
					return false;
			} else if (!port.equals(other.port))
				return false;
			if (scheme == null) {
				if (other.scheme != null)
					return false;
			} else if (!scheme.equals(other.scheme))
				return false;
			return true;
		}
	}

	public ArrayList<String> actions = new ArrayList<String>();
	public ArrayList<String> categories = new ArrayList<String>();
	public Data data = new Data();

	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < actions.size(); i++) {
			builder.append(String.format("%s(%d): %s\n", "Action", i,
					actions.get(i)));
		}

		for (int i = 0; i < categories.size(); i++) {
			builder.append(String.format("%s(%d): %s\n", "Category", i,
					categories.get(i)));
		}
		builder.append("Data:");
		builder.append(String.format("%s\n", data));

		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
		result = prime * result
				+ ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntentFilter other = (IntentFilter) obj;
		if (actions == null) {
			if (other.actions != null)
				return false;
		} else if (!actions.equals(other.actions))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
}
