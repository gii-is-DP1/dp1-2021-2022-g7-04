package org.springframework.samples.minesweeper.configuration;

import org.hibernate.envers.RevisionListener;
import org.springframework.samples.minesweeper.audit.Revision;

public class CustomRevisionListener implements RevisionListener {

	public void newRevision(Object revisionEntity) {
		final Revision revision = (Revision) revisionEntity;
	}
}