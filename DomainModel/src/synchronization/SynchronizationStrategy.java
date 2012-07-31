package synchronization;

/**
 * Component that must be extended by all synchronization strategies (<i>Strategy</i> pattern).
 * Synchronization may be done in several ways, which are established at application level, not
 * at domain level. For example synchronization is done differently on mobile devices due to bandwidth
 * limitations (thus the use of <i>important files</i> list). The synchronization strategy may vary
 * also depending on the file size, for larger files (hundreds of MB) a piece-based synchronization
 * is preferable.
 *
 */

public abstract class SynchronizationStrategy {
	ConflictResolutionStrategy conflictResolution;
	public abstract void synchronize();
}
