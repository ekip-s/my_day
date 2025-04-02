CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $BODY$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$BODY$;

CREATE TRIGGER update_recurring_events_modtime
BEFORE UPDATE ON recurring_events
FOR EACH ROW EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER update_single_events_modtime
BEFORE UPDATE ON single_events
FOR EACH ROW EXECUTE FUNCTION update_modified_column();